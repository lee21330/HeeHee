let selectRoomId; // 선택한 채팅방 번호
let selectReceiverId; // 채팅방 상대 Id
let selectReceiverName; // 채팅방 상대 닉네임

// 문서 로딩 완료 후 수행할 기능
document.addEventListener("DOMContentLoaded", ()=>{
   
   // 채팅방 목록에 클릭 이벤트 추가
   roomListAddEvent(); 
	
	// 채팅방 리스트 뿌리기
	fetchChatRoomList();
	setInterval(function() {
		fetchChatRoomList();
	}, 1500);
	
   // 보내기 버튼에 이벤트 추가
   // send.addEventListener("click", sendMessage);
});

// 채팅방 목록에서 채팅방 선택 시 추가되는 이벤트
function roomListAddEvent(){
  
   const chattingItemList = document.getElementsByClassName("chatting-item");
   
   for(let item of chattingItemList){
      item.addEventListener("click", e => {
       
         
        
         // 전역변수에 채팅방 번호, 상대 id, 상대 닉네임 저장
         selectRoomId = item.getAttribute("room-id");
         selectReceiverId = item.getAttribute("receiver-id");

         selectReceiverName = item.children[1].children[0].children[0].innerText;

         const unreadCountElem = item.querySelector(".unread-count");
            if (unreadCountElem) {
                unreadCountElem.remove();
            }

         // 모든 채팅방에서 select 클래스를 제거
         for(let it of chattingItemList) it.classList.remove("select")
   
         // 현재 클릭한 채팅방에 select 클래스 추가
         item.classList.add("select");
   
         // 비동기로 메시지 목록을 조회하는 함수 호출
         selectChattingFn();
         
         fetch("/heehee/chatting/read",{
             method : "PUT",
             headers : {"Content-Type": "application/json"},
             body : JSON.stringify({"chatRoomId" : selectRoomId, "loginUserId" : loginMemberNo})
            })
            .then(resp => resp.text())
            .then(result => console.log(result))
            .catch(err => console.log(err));
      });
   }
}

//roomDetail.chatProductDTO.productImg
        
//$.each(var message :roomDetail.chatMessageDTO) {
//        	message.msgId}

function selectChattingFn(){
    fetch("/heehee/chatting/${selectRoomId}")
    .then(response=>response.json())
    .then(roomDetail => {
    
        //채팅 메세지 위 영역: 상대방 닉네임, 판매 물품 정보(이미지, 가격, 제품명)
        const contentHeader=document.querySelector("content-header");
        
        contentHeader.innerHTML = "";
        
        const receiverNickname = document.createElement("p");
        receiverNickname.classList.add("receiver-nickname");
        receiverNickname.innerText = selectReceiverName;
        
        const sellingInfo = document.createElement("div");
        receiverNickname.classList.add("selling-info");
        
        const sellingImage = document.createElement("img");
        sellingImage.classList.add("selling-image");
        
        const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${roomDetail.chatProductDTO.productImg}`;
        listProfile.setAttribute("src", imgUrl);
        
        const priceName = document.createElement("div");
        priceName.classList.add("sprice-name");
        
        const sellingPrice = document.createElement("p");
    })
    .catch(err => console.log(err));
}

//채팅방 목록 1.5초마다 불러오기
function fetchChatRoomList() {
    fetch("/heehee/chatting/roomList")
    .then(response => response.json())
    .then(data => {
        	console.log(data);
            updateChatRoomList(data);
    })
    .catch(err => console.log(err));
}

//채팅방 목록 업데이트 함수
function updateChatRoomList(data) {
    // 채팅방 목록 출력 영역 선택
    const chattingList = document.querySelector(".chatting-list");

    // 현재 목록에 있는 채팅방의 ID를 추적하기 위한 집합
    const existingChatRoomIds = new Set();
    
    // 조회한 채팅방 목록을 순회
    for (let room of data) {
        existingChatRoomIds.add(room.id);

        // 채팅방 아이템을 찾기
        const existingChatRoom = chattingList.querySelector(`li[room-id="${room.id}"]`);
        
        // 이미 있는 채팅방이면 unreadCount, sendTime, lastContent만 업데이트
        if (existingChatRoom) {
            const content = existingChatRoom.querySelector(".recent-message").innerHTML;
            const unreadCount = existingChatRoom.querySelector(".unread-count").innerText;
            
            //안 읽은 메시지가 있고 메시지가 새로 와서 업데이트해야하는 경우
            if(unreadCount>0 && content!=room.lastcontent){
                existingChatRoom.querySelector(".unread-count").innerText = room.unreadcount;
          	    existingChatRoom.querySelector(".send-time").innerText = room.sendtime;
          	    existingChatRoom.querySelector(".recent-message").innerHTML = room.lastcontent;
          	    
          	    //업데이트 후 목록 맨 위로 이동
          	    chattingList.insertBefore(existingChatRoom, chattingList.children[1]);
            }
            
        } else {
            // 새로운 채팅방이면 목록 위에 추가
            const li = document.createElement("li");
            li.classList.add("chatting-item");
            li.setAttribute("room-id", room.id);
            li.setAttribute("receiver-id", room.receiverid);

            // item-header 부분
            const itemHeader = document.createElement("div");
            itemHeader.classList.add("item-header");

            const listProfile = document.createElement("img");
            listProfile.classList.add("receiver-image");

            // 프로필 사진 없는 경우 수정하기
            const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${room.receiverImg}`;
            listProfile.setAttribute("src", imgUrl);

            itemHeader.append(listProfile);

            // item-body 부분
            const itemBody = document.createElement("div");
            itemBody.classList.add("item-body");

            const nameCount = document.createElement("div");
            nameCount.classList.add("name-count");

            const receiverNickname = document.createElement("p");
            receiverNickname.classList.add("receiver-nickname");
            receiverNickname.innerText = room.receivernickname;

            //const unreadCount = document.createElement("p");
            //unreadCount.classList.add("unread-count");
            //unreadCount.innerText = room.unreadcount;

            nameCount.append(receiverNickname);

            const messageContainer = document.createElement("div");
            messageContainer.classList.add("message-container");

            const recentMessage = document.createElement("span");
            recentMessage.classList.add("recent-message");
            recentMessage.innerHTML = room.lastcontent;

            const sendTime = document.createElement("span");
            sendTime.classList.add("send-time");
            sendTime.innerText = room.sendtime;

            messageContainer.append(recentMessage, sendTime);

            itemBody.append(nameCount, messageContainer);

            // 현재 채팅방을 보고 있지 않고, 읽지 않은 메시지 개수가 0개 이상인 경우 -> 읽지 않은 메시지 개수 표시
            if(room.notReadCount > 0 && room.id != selectRoomId){
                const unreadCount = document.createElement("p");
                unreadCount.classList.add("unread-count");
                unreadCount.innerText = room.unreadcount;
                nameCount.append(unreadCount);
            
           // 현재 채팅방을 보고 있는 경우
           // 비동기로 해당 채팅방 메시지를 읽음으로 표시
           }

            li.append(itemHeader, itemBody);

            // 새로운 채팅방을 목록의 맨 위에 추가
            chattingList.insertBefore(li, chattingList.children[1]);
        }
    }
}
