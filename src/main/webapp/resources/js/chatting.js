document.addEventListener("DOMContentLoaded", function() {
    // 채팅방 목록에서 각 항목에 클릭 이벤트 리스너 등록
    const chattingItems = document.querySelectorAll(".chatting-item");
    chattingItems.forEach(function(item) {
        item.addEventListener("click", function() {
            // 클릭된 채팅방의 room-id와 loginuserid 속성 가져오기
            const roomId = item.getAttribute("room-id");
            
            // 여기에 채팅방 입장 로직 추가 (예: URL 이동, AJAX 호출 등)
            enterChatRoom(roomId, loginMemberNo); // 예시 함수 호출
            
        });
    });
});

$(document).ready(function() {
    // 주기적으로 서버에서 데이터를 가져오는 함수
    function updateChatRoom() {
        $.ajax({
            url: '/chatting/roomList', // 서버에서 데이터를 가져올 URL
            method: 'GET',
            dataType: 'json',
            success: function(response) {
                updateChatRoomList(response); // 가져온 데이터를 DOM에 반영하는 함수 호출
            },
            error: function(error) {
                console.log('Error fetching chat room updates:', error);
            }
        });
    }

    // 주기적으로 데이터를 갱신하기 위해 setInterval 사용
    setInterval(function() {
        updateChatRoom();
    }, 1500); //1.5초마다 실행
});

function updateChatRoomList(roomList) {
    // 채팅방 목록 출력 영역 선택
    const chattingList = document.querySelector(".chatting-list");

    // 현재 목록에 있는 채팅방의 ID를 추적하기 위한 집합
    const existingChatRoomIds = new Set();
    
    // 조회한 채팅방 목록을 순회
    for (let room of roomList) {
        existingChatRoomIds.add(room.id);

        // 채팅방 아이템을 찾기
        const existingChatRoom = chattingList.querySelector(`li[room-id="${room.id}"]`);

        if (existingChatRoom) {
            // 이미 있는 채팅방이면 unreadCount, sendTime, lastContent만 업데이트
            existingChatRoom.querySelector(".unread-count").innerText = room.unreadCount;
            existingChatRoom.querySelector(".send-time").innerText = room.sendTime;
            existingChatRoom.querySelector(".recent-message").innerHTML = room.lastContent;
        } else {
            // 새로운 채팅방이면 목록 위에 추가
            const li = document.createElement("li");
            li.classList.add("chatting-item");
            li.setAttribute("room-id", room.id);
            li.setAttribute("receiver-id", room.receiverId);

            // item-header 부분
            const itemHeader = document.createElement("div");
            itemHeader.classList.add("item-header");

            const listProfile = document.createElement("img");
            listProfile.classList.add("receiver-image");

            // 프로필 사진 없는 경우 수정하기
            const imgUrl = room.receiverImg 
                ? `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${room.receiverImg}`
                : "/resources/images/user.png";
            listProfile.setAttribute("src", imgUrl);

            itemHeader.append(listProfile);

            // item-body 부분
            const itemBody = document.createElement("div");
            itemBody.classList.add("item-body");

            const nameCount = document.createElement("div");
            nameCount.classList.add("name-count");

            const receiverNickname = document.createElement("p");
            receiverNickname.classList.add("receiver-nickname");
            receiverNickname.innerText = room.receiverNickname;

            const unreadCount = document.createElement("p");
            unreadCount.classList.add("unread-count");
            unreadCount.innerText = room.unreadCount;

            nameCount.append(receiverNickname, unreadCount);

            const messageContainer = document.createElement("div");
            messageContainer.classList.add("message-container");

            const recentMessage = document.createElement("span");
            recentMessage.classList.add("recent-message");
            recentMessage.innerHTML = room.lastContent;

            const sendTime = document.createElement("span");
            sendTime.classList.add("send-time");
            sendTime.innerText = room.sendTime;

            messageContainer.append(recentMessage, sendTime);

            itemBody.append(nameCount, messageContainer);

            li.append(itemHeader, itemBody);

            // 새로운 채팅방을 목록의 맨 위에 추가
            chattingList.insertBefore(li, chattingList.firstChild);
        }
    }

    // 기존 채팅방 목록에서 더 이상 존재하지 않는 항목 제거
    const allChatRooms = chattingList.querySelectorAll(".chatting-item");
    allChatRooms.forEach(chatRoom => {
        const roomId = chatRoom.getAttribute("room-id");
        if (!existingChatRoomIds.has(roomId)) {
            chattingList.removeChild(chatRoom);
        }
    });
}
