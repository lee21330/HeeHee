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

// 비동기로 메시지 목록을 조회하는 함수
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
        
        const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/${roomDetail.roomProductDTO.productType}/${roomDetail.roomProductDTO.productImg}`;
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
        
        sellingInfo.append(sellingImage, priceName);
        
        // 버튼 생성 및 판매자/구매자별로 내용 다르게 설정
        const status = roomDetail.roomProductDTO.status;
        
        if (status == '구매자') {
            const payButton = document.createElement("button");
            payButton.classList.add("payEdit");
        
            payButton.innerText = "결제하기";
            
            sellingInfo.append(payButton);
            
            payButton.addEventListener("click", () => {
                // 구매자 버튼 클릭 이벤트 처리
                pay(roomDetail.roomMessageDTO[0].sender);
            });
        } else if(status == '판매자'){
            if(roomDetail.roomProductDTO.productType=='sell'){
                const payButton = document.createElement("button");
                payButton.classList.add("payEdit");
            
                payButton.innerText = "가격 수정하기";
                
                sellingInfo.append(payButton);
            
                payButton.addEventListener("click", () => {
                    // 판매자 버튼 클릭 이벤트 처리
                    editPrice(roomDetail.roomProductDTO.productPrice, roomDetail.roomProductDTO.productSeq);
                });
            } 
        } 
        
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
        
        contentBody.scrollTop = contentBody.scrollHeight; //스크롤 최하단으로 이동
    })
    .catch(err => console.log(err));
}

//모달 관련
const chattingModal = document.querySelector(".chattingModal");

//결제하기 함수
function pay(sender){
    chattingModal.style.display='block';
    chattingModal.innerHTML="";
    
    //결제에 성공한 경우
    //const modalContent2 = document.createElement("div");
    //modalContent2.classList.add("modal-content2");
    
    //const Info2 = document.createElement("div");
    //Info2.innerText='결제에 성공하였습니다!';
    
    //modalContent2.append(Info2);
    
    //chattingModal.append(modalContent2);
    
    //결제에 실패한 경우
    const modalContent = document.createElement("div");
    modalContent.classList.add("modal-content");
    
    const Info = document.createElement("div");
    Info.classList.add("info");
    Info.innerText='포인트가 부족합니다.';
    
    const currentAccount = document.createElement("div");
    currentAccount.classList.add("current-account");
    //계좌번호 추가할것
    currentAccount.innerText='현재 계좌 : ';
    
    const currentInput3 = document.createElement("div");
    currentInput3.classList.add("current-input3");
    
    const ciElement3 = document.createElement('input');
    ciElement3.className = 'add-point';
    ciElement3.type = 'number';
    ciElement3.placeholder = '충전할 금액을 입력해주세요. (원)';
    
    currentInput3.append(ciElement3);
    
    const chModalBtn3 = document.createElement("div");
    chModalBtn3.classList.add("chModalBtn3");
    
    const submitPrice = document.createElement("button");
    submitPrice.classList.add("submit-price");
    submitPrice.innerText='충전하기';
    
    submitPrice.addEventListener('click', ()=>{
        chattingModal.style.display='none';
        });
    
    const cancel = document.createElement("button");
    cancel.classList.add("cancel");
    cancel.innerText='취소하기';
    
    cancel.addEventListener('click', ()=>{
        chattingModal.style.display='none';
    });
    
    chModalBtn3.append(submitPrice, cancel);
    
    modalContent.append(Info, currentAccount, currentInput3, chModalBtn3);
    
    chattingModal.append(modalContent);
}

//가격 수정하기 함수
function editPrice(productPrice, productSeq){
    chattingModal.style.display='block';
    chattingModal.innerHTML="";
    
    const modalContent = document.createElement("div");
    modalContent.classList.add("modal-content");
    
    const currentPrice = document.createElement("div");
    currentPrice.classList.add("current-price");
    currentPrice.innerText='기존 가격 : ' + productPrice + '원';
    
    const currentInput = document.createElement("div");
    currentInput.classList.add("current-input");
    
    const ciElement = document.createElement('input');
    ciElement.className = 'new-price';
    ciElement.type = 'number';
    ciElement.placeholder = '수정할 가격을 입력해주세요. (원)';
    
    currentInput.append(ciElement);
    
    const chModalBtn = document.createElement("div");
    chModalBtn.classList.add("chModalBtn");
    
    const submitPrice = document.createElement("button");
    submitPrice.classList.add("submit-price");
    submitPrice.innerText='수정하기';
    
    submitPrice.addEventListener('click', ()=>{
        chattingModal.style.display='none';
        const newPrice=document.querySelector('.new-price').value;
        document.querySelector(".selling-price").innerText = newPrice + '원';
        // console.log(newPrice, productSeq);
        fetch("/heehee/chatting/price",{
             method : "PUT",
             headers : {"Content-Type": "application/json"},
             body : JSON.stringify({"newPrice" : newPrice, "productSeq" : productSeq})
            })
            .then(resp => resp.text())
            .then(result => console.log(result))
            .catch(err => console.log(err));
        });
        
    
    const cancel = document.createElement("button");
    cancel.classList.add("cancel");
    cancel.innerText='취소하기';
    
    cancel.addEventListener('click', ()=>{
        chattingModal.style.display='none';
    });
    
    chModalBtn.append(submitPrice, cancel);
    
    modalContent.append(currentPrice, currentInput, chModalBtn);
    
    chattingModal.append(modalContent);
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