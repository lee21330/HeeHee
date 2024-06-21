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

function selectChattingFn(){
    fetch(`/heehee/chatting/${selectRoomId}`)
    .then(response=>response.json())
    .then(roomDetail => {
    
        const chattingContent = document.querySelector(".chatting-content");
        chattingContent.innerHTML="";
    
        //채팅 메세지 위 영역: 상대방 닉네임, 판매 물품 정보(이미지, 가격, 제품명)
        const contentHeader = document.createElement("div");
        contentHeader.classList.add("content-header");
        
        const receiverNickname = document.createElement("p");
        receiverNickname.classList.add("receiver-nickname");
        receiverNickname.innerHTML = selectReceiverName;
        
        const sellingInfo = document.createElement("div");
        sellingInfo.classList.add("selling-info");
        
        const sellingImage = document.createElement("img");
        sellingImage.classList.add("selling-image");
        
        const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/sell/${roomDetail.roomProductDTO.productImg}`;
        sellingImage.setAttribute("src", imgUrl);
        
        const priceName = document.createElement("div");
        priceName.classList.add("price-name");
        
        const sellingPrice = document.createElement("p");
        sellingPrice.classList.add("selling-price");
        sellingPrice.innerText = roomDetail.roomProductDTO.productPrice + '원';
        
        const sellingName = document.createElement("p");
        sellingName.classList.add("selling-name");
        sellingName.innerHTML = roomDetail.roomProductDTO.productName;
        
        priceName.append(sellingPrice, sellingName);
        
        // 버튼 생성 및 판매자/구매자별로 내용 다르게 설정
        const payButton = document.createElement("button");
        payButton.classList.add("payEdit");
        
        const status = roomDetail.roomProductDTO.status;
        
        if (status == '구매자') {
            payButton.innerText = "결제하기";
            payButton.addEventListener("click", () => {
                // 구매자 버튼 클릭 이벤트 처리
                pay(roomDetail.roomMessageDTO[0].sender);
            });
        } else if(status == '판매자'){
            payButton.innerText = "가격 수정하기";
            payButton.addEventListener("click", () => {
                // 판매자 버튼 클릭 이벤트 처리
                editPrice(roomDetail.roomMessageDTO[0].sender);
            });
        }
        
        sellingInfo.append(sellingImage, priceName, payButton);
        
        contentHeader.append(receiverNickname, sellingInfo);
        
        const contentBody = document.createElement("div");
        contentBody.classList.add("content-body");
        
        const messageList = document.createElement("div");
        messageList.classList.add("message-list");
        
        if(roomDetail.roomMessageDTO.length>0){
            
            for(const message of roomDetail.roomMessageDTO){
                if(message.sender == loginMemberNo){
                    if(message.content.indexOf('[img_asdfzv]')==-1){
                    const myChat = document.createElement("div");
                    myChat.classList.add("my-chat");
                    
                    const chatDate = document.createElement("span");
                    chatDate.classList.add("chatDate");
                    chatDate.innerHTML = message.sendTime + ' 읽음';
                    
                    const chat = document.createElement("p");
                    chat.classList.add("chat");
                    chat.innerHTML = message.content;
                    
                    myChat.append(chatDate, chat);
                    
                    messageList.append(myChat);
                    }
                    
                    //사진 띄워주기 https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/chat/~~
                   
                }
                else if(message.sender != loginMemberNo){
                    if(message.content.indexOf('[img_asdfzv]')==-1){
                        const targetChat = document.createElement("div");
                        targetChat.classList.add("target-chat");
                    
                        const chat = document.createElement("p");
                        chat.classList.add("chat");
                        chat.innerHTML = message.content;
                    
                        const chatDate = document.createElement("span");
                        chatDate.classList.add("chatDate");
                    
                        let read = '안 읽음';
                    
                        if(message.readCheck == 'Y'){
                            read = '읽음';
                        }
                    
                        chatDate.innerHTML = message.sendTime + ' ' + read;
                    
                        targetChat.append(chat, chatDate);
                    
                        messageList.append(targetChat);
                    }
                }
            }
            contentBody.append(messageList);
        }
        
        const chattingInput = document.createElement("div");
        chattingInput.classList.add("chatting-input");
        
        const inputPhoto = document.createElement("img");
        inputPhoto.classList.add("input-photo");
        
        const imgUrl1 = "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/camera.png";
        inputPhoto.setAttribute("src", imgUrl1);
        
        const inputDiv = document.createElement("div");
        
        const inputElement = document.createElement('input');
        inputElement.className = 'input-area';
        inputElement.type = 'text';
        
        inputDiv.append(inputElement);
        
        const inputSend = document.createElement("img");
        inputSend.classList.add("input-send");
        
        const imgUrl2 = "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/send.png";
        inputSend.setAttribute("src", imgUrl2);
        
        chattingInput.append(inputPhoto, inputDiv, inputSend);
        
        chattingContent.append(contentHeader, contentBody, chattingInput);
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
           }

            li.append(itemHeader, itemBody);

            // 새로운 채팅방을 목록의 맨 위에 추가
            chattingList.insertBefore(li, chattingList.children[1]);
        }
    }
}