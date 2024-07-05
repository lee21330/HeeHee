let selectRoomId; // 선택한 채팅방 번호
let selectReceiverId; // 채팅방 상대 Id
let selectReceiverName; // 채팅방 상대 닉네임
var ChatStompClient = null; // stomp 소켓 클라이언트 전역 설정

let selectedFiles = []; // 첨부한 이미지 파일 목록

// 문서 로딩 완료 후 수행할 기능

document.addEventListener("DOMContentLoaded", ()=>{
   
   // 채팅방 목록에 클릭 이벤트 추가
   roomListAddEvent(); 
	
	// 채팅방 리스트 뿌리기
	fetchChatRoomList();
	setInterval(function() {
		fetchChatRoomList();
		//selectChattingFn();
	}, 1500);
	
});

// 채팅룸 커넥트
function chatConnect(chatRoomId) {
	disconnect();
	var ChatSocket = new SockJS('/heehee/chatws');
	ChatStompClient = Stomp.over(ChatSocket);
	ChatStompClient.connect({}, function (frame) {
	    ChatStompClient.send("/app/joinRoom", {}, JSON.stringify({"userId": loginMemberNo, "roomId": chatRoomId}));
	        ChatStompClient.subscribe('/topic/chatroom/' + chatRoomId, function (chatMessage) {
	        	var json = JSON.parse(chatMessage.body);
	        	if(json.userId != null) {
					selectChattingFn();
	        	} else {
		            showMessage(json);
	            }
	        });
        });
}

// 연결 끊기
function disconnect() {
    if (ChatStompClient !== null) {
        ChatStompClient.disconnect();
    }
    console.log("Disconnected");
}

// 채팅 보여주기
function showMessage(chatMessage){
	if(document.querySelector(".noneRoomChat")){document.querySelector(".noneRoomChat").remove();}
    const messageList = document.querySelector(".message-list");
    
    const contentBody = document.querySelector(".content-body");
    
    if(chatMessage.sender == loginMemberNo){
        
        if(chatMessage.imgs.length == 0){
        
            const myChat = document.createElement("div");
            myChat.classList.add("my-chat");
            
            const chatDate = document.createElement("span");
            chatDate.classList.add("chatDate");
            let read = '안 읽음';
                    
            if(chatMessage.readCheck == 'Y'){
                read = '읽음';
            }
                    
            chatDate.innerHTML = chatMessage.sendTime.substr(11,5) + read;
                    
            const chat = document.createElement("p");
            chat.classList.add("chat");
            chat.innerHTML = chatMessage.content;
                    
            myChat.append(chatDate, chat);
            messageList.append(myChat);
            contentBody.append(messageList);
    
            contentBody.scrollTop = contentBody.scrollHeight;
        }
        else{
            chatMessage.imgs.forEach(img => {
            
                    const myChat = document.createElement("div");
                    myChat.classList.add("my-chat");
            
                    const chatDate = document.createElement("span");
                    chatDate.classList.add("chatDate");
                    let read = '안 읽음';
                    
                    if(chatMessage.readCheck == 'Y'){
                        read = '읽음';
                    }
                    
                    chatDate.innerHTML = chatMessage.sendTime.substr(11,5) + read;
                    
                    const chatImage = document.createElement("img");
                    chatImage.classList.add("chat-image");
                        
                    const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/${img}`;
                        
                    chatImage.setAttribute("src", imgUrl);
                    chatImage.setAttribute("alt", "image");
                    
                    myChat.append(chatDate, chatImage);
                    
                    messageList.append(myChat);
                    
                    contentBody.append(messageList);
    
                    contentBody.scrollTop = contentBody.scrollHeight;
            });
        }
    }
    else if(chatMessage.sender != loginMemberNo){
         if(chatMessage.imgs.length == 0){
            const targetChat = document.createElement("div");
            targetChat.classList.add("target-chat");
          
            const chatDate = document.createElement("span");
            chatDate.classList.add("chatDate");
            chatDate.innerHTML = chatMessage.sendTime.substr(11,5) + ' 읽음';
            
            const chat = document.createElement("p");
            chat.classList.add("chat");
            chat.innerHTML = chatMessage.content;
                    
            targetChat.append(chat, chatDate);
                    
            messageList.append(targetChat);
            
            contentBody.append(messageList);
    
            contentBody.scrollTop = contentBody.scrollHeight;
          } else{
              chatMessage.imgs.forEach(img => {
                  const targetChat = document.createElement("div");
                  targetChat.classList.add("target-chat");
          
                  const chatDate = document.createElement("span");
                  chatDate.classList.add("chatDate");
                  chatDate.innerHTML = chatMessage.sendTime.substr(11,5) + ' 읽음';
                  const chatImage = document.createElement("img");
                  chatImage.classList.add("chat-image");
                        
                  const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/${img}`;
                         
                  chatImage.setAttribute("src", imgUrl);
                  chatImage.setAttribute("alt", "image");
              
                  targetChat.append(chatImage, chatDate);
                    
                  messageList.append(targetChat);
            
                  contentBody.append(messageList);
            
                  contentBody.scrollTop = contentBody.scrollHeight;
              });
          }
     }
    
}

// 채팅 보내기
function sendMessage(inputValue){
    if(inputValue!="" || selectedFiles.length > 0){
        const offset = new Date().getTimezoneOffset() * 60000;
        const today = new Date(Date.now() - offset);
        const sendTime = today.toISOString().replace('T', ' ').replace('Z', '').substring(0,19);
        
        if (selectedFiles.length > 0) {
            const formData = new FormData();

            selectedFiles.forEach(file => {
                formData.append('imgs', file);
            });
            
            fetch("/heehee/chatting/upload/image", {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                const message = {
                    'roomId': selectRoomId,
                    'sender': loginMemberNo,
                    'receiver': selectReceiverId,
                    'content': inputValue,
                    'sendTime': sendTime,
                    'imgs': data
                };
                stompClient.send("/app/chat", {}, JSON.stringify(message));
                selectedFiles = [];  // 이미지 전송 후 파일 초기화
                imageInput.value = "";  // 파일 입력 초기화
            })
            .catch(err => console.log(err));
        } else {
            const message = {
                'roomId': selectRoomId,
                'sender': loginMemberNo,
                'receiver': selectReceiverId,
                'content': inputValue,
                'sendTime': sendTime,
                'imgs': []
            };
        
        stompClient.send("/app/chat", {}, JSON.stringify(message));
    }
  }
}

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
         
         // 채팅 메시지가 있는 경우
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
            
          //소켓 연결
          chatConnect(selectRoomId);
      });
      
   }
}

function payForSell(payName, amount, sellSeq, payButton) {
	$.ajax({
	    url: '/heehee/pay/before',
	    method: 'POST',
	    data: {
	    	"payName" : payName,
	    	"amount" : amount,
	    	"sellSeq" : sellSeq
	    	},
	    success: function (data, status, xhr) {
	    	payment(payName,data, payButton);
	    },
	    error: function (data, status, err) {
	    	console.log(err);
	    }
	});
}

function payment(payName, payInfo, payButton) {
	IMP.init("imp22447463");
	IMP.request_pay(
			  {
			    pg: "html5_inicis.INIpayTest", //테스트 시 html5_inicis.INIpayTest 기재
			    pay_method: "card",
			    merchant_uid: payInfo.paySeq, //상점에서 생성한 고유 주문번호
			    name: payName,
			    amount: 1,
			    buyer_email: payInfo.buyerEmail,
			    buyer_name: payInfo.buyerId,
			    buyer_tel: payInfo.buyerTel, //필수 파라미터 입니다.
			    buyer_addr: payInfo.buyerTel,
			    buyer_postcode: "123-456",
			    m_redirect_url: "{모바일에서 결제 완료 후 리디렉션 될 URL}",
			    escrow: true, //에스크로 결제인 경우 설정
			    vbank_due: "20240725",
			    bypass: {
			      acceptmethod: "noeasypay", // 간편결제 버튼을 통합결제창에서 제외(PC)
			      P_RESERVED: "noeasypay=Y", // 간편결제 버튼을 통합결제창에서 제외(모바일)
			    },
			    period: {
			      from: "20240101", //YYYYMMDD
			      to: "20241231", //YYYYMMDD
			    },
			  }, function (rsp) {
			    if(rsp.success) {
			    	completePayment(payInfo, payButton);
			    	return true;
			    }
			  }
			);
}

function completePayment(payInfo, payButton) {
	$.ajax({
        url: '/heehee/pay/complete',
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ "paySeq": payInfo.paySeq }),
        success: function (data, status, xhr) {
            console.log(data);
            console.log(status);
            console.log(xhr);
            fetch("/heehee/chatting/reserve",{
                method : "POST",
                headers : {"Content-Type": "application/json"},
                body : JSON.stringify({
                "buyerId" : selectReceiverId,
                "productSeq" : payInfo.sellSeq
                })
            })
            .then(resp => resp.text())
            .then(result => console.log(result))
            .catch(err => console.log(err));
            payButton.disabled = true;
            return true;
        },
        error: function (data, status, err) {
            console.log(err);
        }
    });
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
        receiverNickname.innerHTML = selectReceiverName ? selectReceiverName : "(알 수 없음)";
        
        const sellingInfo = document.createElement("div");
        sellingInfo.classList.add("selling-info");
        
        const sellingImage = document.createElement("img");
        sellingImage.classList.add("selling-image");
        
        const imgName = roomDetail.roomProductDTO.productImg ? `${roomDetail.roomProductDTO.productType}/${roomDetail.roomProductDTO.productImg}` : "mypage/logo_profile.jpg";
        
        const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/${imgName}`;
        sellingImage.setAttribute("src", imgUrl);
        
        const priceName = document.createElement("div");
        priceName.classList.add("price-name");
        
        const sellingPrice = document.createElement("p");
        sellingPrice.classList.add("selling-price");
        const price = roomDetail.roomProductDTO.productPrice ? roomDetail.roomProductDTO.productPrice : 0;
        const formatPrice = price.toLocaleString('ko-KR');
        //console.log(formatPrice);
        sellingPrice.innerText = formatPrice + '원';
        
        const sellingName = document.createElement("p");
        sellingName.classList.add("selling-name");
        sellingName.innerHTML = roomDetail.roomProductDTO.productName ? roomDetail.roomProductDTO.productName : "(삭제된 게시물)";
        
        priceName.append(sellingPrice, sellingName);
        
        sellingInfo.append(sellingImage, priceName);
        
        // 버튼 생성 및 판매자/구매자별로 내용 다르게 설정
        const status = roomDetail.roomProductDTO.status;
        const payStatus = roomDetail.roomProductDTO.payStatus;
        //console.log(payStatus);
        
        const buttonDiv = document.createElement("div");
        buttonDiv.classList.add("button-div");
        
        //중고 제품인 경우에만 버튼 추가
        if(roomDetail.roomProductDTO.productType=='sell'){
            if (status == '구매자') {
                if(roomDetail.roomProductDTO.dealStatus == '택배' && roomDetail.roomProductDTO.sellProStatus=='판매중'){
                const payButton = document.createElement("button");
                payButton.classList.add("pay");
        
                payButton.innerText = "결제하기";
            
                //결제 내역 테이블에 동일 구매자id&제품 seq 데이터의 결제상태가 '완료'면 결제하기 버튼 비활성화
                if(payStatus == '완료'){
                    payButton.disabled = true;
                }
            
                payButton.addEventListener("click", () => {
                    // 구매자 버튼 클릭 이벤트 처리
                    const payName = roomDetail.roomProductDTO.productName;
                    const amount = roomDetail.roomProductDTO.productPrice;
                    const sellSeq = roomDetail.roomProductDTO.productSeq;
                    payForSell(payName, amount, sellSeq, payButton);
                });
            
                buttonDiv.append(payButton);
                }
            
            } else if(status == '판매자'){
                //약속잡기 버튼
                if(roomDetail.roomProductDTO.dealStatus == '직거래'){
                    if(roomDetail.roomProductDTO.sellProStatus=='판매중'){
                        const rsvButton = document.createElement("button");
                        rsvButton.classList.add("reserve");
                        rsvButton.innerText = "약속 잡기";
                        // 약속 잡기 버튼 클릭 이벤트 처리
                        rsvButton.addEventListener("click", () => {
                            if(rsvButton.innerText == "약속 잡기"){
                                reserve(roomDetail.roomProductDTO.productSeq);
                                rsvButton.innerText = "약속 취소";
                            }
                            else if(rsvButton.innerText == "약속 취소"){
                                cancelReserve(roomDetail.roomProductDTO.productSeq);
                                rsvButton.innerText = "약속 잡기";
                            }
                        });
                        buttonDiv.append(rsvButton);
                    }
                    //판매 제품의 판매 상태가 예약중이면 약속 잡기 버튼 비활성화
                    else if(roomDetail.roomProductDTO.sellProStatus=='예약중'){
                        const rsvButton = document.createElement("button");
                        rsvButton.classList.add("reserve");
                        rsvButton.innerText = "약속 취소";
                         // 약속 취소 버튼 클릭 이벤트 처리
                        rsvButton.addEventListener("click", () => {
                            if(rsvButton.innerText == "약속 잡기"){
                                reserve(roomDetail.roomProductDTO.productSeq);
                                rsvButton.innerText = "약속 취소";
                            }
                            else if(rsvButton.innerText == "약속 취소"){
                                cancelReserve(roomDetail.roomProductDTO.productSeq);
                                rsvButton.innerText = "약속 잡기";
                            }
                        });
                        buttonDiv.append(rsvButton);
                    }
                }
                if(roomDetail.roomProductDTO.sellProStatus=='판매중'){
                    const payButton = document.createElement("button");
                    payButton.classList.add("edit");
                
                    payButton.innerText = "가격 수정하기";
                
                    payButton.addEventListener("click", () => {
                        // 판매자 버튼 클릭 이벤트 처리
                        editPrice(roomDetail.roomProductDTO.productPrice, roomDetail.roomProductDTO.productSeq);
                    });
                    buttonDiv.append(payButton);
                }
            }
        }
        
        sellingInfo.append(buttonDiv);
        
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
                    
                    let read = '안 읽음';
                    
                    if(message.readCheck == 'Y'){
                        read = '읽음';
                    }
                    
                    chatDate.innerHTML = message.sendTime + ' ' + read;
                    
                    const chat = document.createElement("p");
                    chat.classList.add("chat");
                    chat.innerHTML = message.content;
                    
                    myChat.append(chatDate, chat);
                    
                    messageList.append(myChat);
                    }
                    //사진 띄워주기
                    else if(message.content.indexOf('[img_asdfzv]')!=-1){
                        const myChat = document.createElement("div");
                        myChat.classList.add("my-chat");
                    
                        const chatDate = document.createElement("span");
                        chatDate.classList.add("chatDate");
                        
                        let read = '안 읽음';
                    
                        if(message.readCheck == 'Y'){
                            read = '읽음';
                        }
                    
                        chatDate.innerHTML = message.sendTime + ' ' + read;
                    
                        const chatImage = document.createElement("img");
                        chatImage.classList.add("chat-image");
                        
                        const imgContent = message.content.replace("[img_asdfzv] ", "");
                        
                        const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/${imgContent}`;
                        
                        chatImage.setAttribute("src", imgUrl);
                        chatImage.setAttribute("alt", "image");
                    
                        myChat.append(chatDate, chatImage);
                    
                        messageList.append(myChat);
                    }
                   
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
                    
                    else if(message.content.indexOf('[img_asdfzv]')!=-1){
                        const targetChat = document.createElement("div");
                        targetChat.classList.add("target-chat");
                        
                        const chatImage = document.createElement("img");
                        chatImage.classList.add("chat-image");
                        
                        const imgContent = message.content.replace("[img_asdfzv] ", "");
                        
                        const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/${imgContent}`;
                        
                        chatImage.setAttribute("src", imgUrl);
                        chatImage.setAttribute("alt", "image");
                    
                        const chatDate = document.createElement("span");
                        chatDate.classList.add("chatDate");
                        
                        let read = '안 읽음';
                    
                        if(message.readCheck == 'Y'){
                            read = '읽음';
                        }
                    
                        chatDate.innerHTML = message.sendTime + ' ' + read;
                    
                        targetChat.append(chatImage, chatDate);
                    
                        messageList.append(targetChat);
                    }
                }
            }
        }
        
        else if(roomDetail.roomMessageDTO.length == 0){
            const noneRoomChat = document.createElement("div");
            noneRoomChat.classList.add("noneRoomChat");
            noneRoomChat.innerHTML = "채팅을 시작해보세요💭";
            contentBody.append(noneRoomChat);
        }
        
        contentBody.append(messageList);
        
        const chattingInput = document.createElement("div");
        chattingInput.classList.add("chatting-input");
        
        const inputPhoto = document.createElement("img");
        inputPhoto.classList.add("input-photo");
        
        const imageInput = document.createElement("input");
        imageInput.classList.add("image-input");
        imageInput.type = "file";
        imageInput.multiple = true;
        imageInput.style.display = "none";
        
        inputPhoto.addEventListener('click', () => {
            imageInput.click();
        });
        
        imageInput.addEventListener('change', (event) => {
            selectedFiles = Array.from(event.target.files);
            sendMessage();
        });
        
        const imgUrl1 = "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/chat/camera.png";
        inputPhoto.setAttribute("src", imgUrl1);
        
        const inputDiv = document.createElement("div");
        
        const inputElement = document.createElement('input');
        inputElement.className = 'input-area';
        inputElement.type = 'text';
        
        inputDiv.append(inputElement);
        
        const inputSend = document.createElement("img");
        inputSend.classList.add("input-send");
        
        if(receiverNickname.innerHTML != "(알 수 없음)" && sellingName.innerHTML != "(삭제된 게시물)"){
            // 전송 버튼 클릭 시 이벤트 추가
            inputSend.addEventListener('click', ()=>{
                sendMessage(inputElement.value);
                //console.log(inputElement.value);
                inputElement.value='';
            });
        
            // 엔터 키 입력 시 이벤트 추가
            inputElement.addEventListener('keydown', (event) => {
                if (event.key === 'Enter') {
                    sendMessage(inputElement.value);
                    inputElement.value = '';
                }
            });
        }
        
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

// 약속잡기 함수
function reserve(productSeq){
    fetch("/heehee/chatting/reserve",{
             method : "POST",
             headers : {"Content-Type": "application/json"},
             body : JSON.stringify({
             "buyerId" : selectReceiverId,
             "productSeq" : productSeq
             })
            })
            .then(resp => resp.text())
            .then(result => console.log(result))
            .catch(err => console.log(err));
        
     const button = document.querySelector(".reserve");
     
     chattingModal.style.display='block';
     chattingModal.innerHTML="";
        
     const modalContent2 = document.createElement("div");
     modalContent2.classList.add("modal-content2");
    
     const Info2 = document.createElement("div");
     Info2.innerText='약속 잡기에 성공하였습니다!';
    
     modalContent2.append(Info2);
    
     chattingModal.append(modalContent2);
        
     setTimeout(()=>{
         chattingModal.style.display='none';
     }, 3000);
     
}

//약속 취소 함수
function cancelReserve(productSeq){
    fetch("/heehee/chatting/reserve/cancel",{
             method : "POST",
             headers : {"Content-Type": "application/json"},
             body : JSON.stringify({
             "buyerId" : selectReceiverId,
             "productSeq" : productSeq
             })
            })
            .then(resp => resp.text())
            .then(result => console.log(result))
            .catch(err => console.log(err));
        
     const button = document.querySelector(".reserve");
     
     chattingModal.style.display='block';
     chattingModal.innerHTML="";
        
     const modalContent2 = document.createElement("div");
     modalContent2.classList.add("modal-content2");
    
     const Info2 = document.createElement("div");
     Info2.innerText='약속 취소에 성공하였습니다!';
    
     modalContent2.append(Info2);
    
     chattingModal.append(modalContent2);
        
     setTimeout(()=>{
         chattingModal.style.display='none';
     }, 3000);
     
}

//채팅방 목록 1.5초마다 불러오기
function fetchChatRoomList() {
    fetch("/heehee/chatting/roomList")
    .then(response => response.json())
    .then(data => {
        	console.log(data);
        	if(data.length>0) $(".noneRoom").hide();
            updateChatRoomList(data);
    })
    .catch(err => console.log(err));
}

//채팅방 목록 업데이트 함수
function updateChatRoomList(data) {
    // 채팅방 목록 출력 영역 선택
    const chattingList = document.querySelector(".chatting-list");
    
    // 조회한 채팅방 목록을 순회
    for (let room of data) {
    
    	let existingChatRoom = null;
    	let li = null;
    
        // 채팅방 아이템을 찾기
        existingChatRoom = chattingList.querySelector(`li[room-id="${room.id}"]`);
        //console.log(chattingList.querySelector(`li[room-id="${room.id}"]`));
        
        
        // 이미 있는 채팅방이면 unreadCount, sendTime, lastContent만 업데이트
        if (existingChatRoom) {
            // receiverid가 null이면 "(알 수 없음)"으로 처리
            if (room.receiverid === null) {
                existingChatRoom.querySelector(".receiver-image").setAttribute("src", "https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/logo_profile.jpg");
                existingChatRoom.querySelector(".receiver-nickname").innerText = "(알 수 없음)";
            }
            
            const content = existingChatRoom.querySelector(".recent-message").innerHTML;
            //안 읽은 메시지가 있거나 메시지가 새로 와서 업데이트해야하는 경우
            if(room.unreadcount>0 || content!=room.lastcontent){
                //기존 unread-count div가 있는 경우
                if(existingChatRoom.querySelector(".unread-count")){
                    existingChatRoom.querySelector(".unread-count").innerText = room.unreadcount;
          	        existingChatRoom.querySelector(".send-time").innerText = room.sendtime;
          	        existingChatRoom.querySelector(".recent-message").innerHTML = room.lastcontent.startsWith("[img_asdfzv]") ? "사진" : room.lastcontent;
                }
                //기존 unread-count div가 없는 경우
                else{
                    if(room.unreadcount>0){
                        const unreadCount = document.createElement("p");
                        unreadCount.classList.add("unread-count");
                        unreadCount.innerText = room.unreadcount;
                    
                        const nameCount = existingChatRoom.querySelector(".name-count");
                       	nameCount.append(unreadCount);
                    }
                   	
                    existingChatRoom.querySelector(".send-time").innerText = room.sendtime;
                    if(room.lastcontent){
          	        	existingChatRoom.querySelector(".recent-message").innerHTML = room.lastcontent.startsWith("[img_asdfzv]") ? "사진" : room.lastcontent;
                	}
                }
          	    
          	    //업데이트 후 목록 맨 위로 이동
          	    //chattingList.insertBefore(existingChatRoom, chattingList.children[1]);
            }
            
        } else {
            // 새로운 채팅방이면 목록 위에 추가
            li = document.createElement("li");
            li.classList.add("chatting-item");
            li.setAttribute("room-id", room.id);
            li.setAttribute("receiver-id", room.receiverid);

            // item-header 부분
            const itemHeader = document.createElement("div");
            itemHeader.classList.add("item-header");

            const receiverImage = document.createElement("img");
            receiverImage.classList.add("receiver-image");

            const imgName = room.receiverimg ? room.receiverimg : "logo_profile.jpg";
            const imgUrl = `https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/mypage/${imgName}`;
            receiverImage.setAttribute("src", imgUrl);

            itemHeader.append(receiverImage);

            // item-body 부분
            const itemBody = document.createElement("div");
            itemBody.classList.add("item-body");

            const nameCount = document.createElement("div");
            nameCount.classList.add("name-count");

            const receiverNickname = document.createElement("p");
            receiverNickname.classList.add("receiver-nickname");
            receiverNickname.innerText = room.receivernickname ? room.receivernickname : "(알 수 없음)";

            nameCount.append(receiverNickname);

            const messageContainer = document.createElement("div");
            messageContainer.classList.add("message-container");

            const recentMessage = document.createElement("span");
            recentMessage.classList.add("recent-message");
            if(room.lastContent!=null){
                existingChatRoom.querySelector(".recent-message").innerHTML = room.lastcontent.startsWith("[img_asdfzv]") ? "사진" : room.lastcontent;
            }
            const sendTime = document.createElement("span");
            sendTime.classList.add("send-time");
            sendTime.innerText = room.sendtime;

            messageContainer.append(recentMessage, sendTime);

            itemBody.append(nameCount, messageContainer);

            // 현재 채팅방을 보고 있지 않고, 읽지 않은 메시지 개수가 0개 이상인 경우 -> 읽지 않은 메시지 개수 표시
            if(room.unreadcount > 0 && room.id != selectRoomId){
                const unreadCount = document.createElement("p");
                unreadCount.classList.add("unread-count");
                unreadCount.innerText = room.unreadcount;
                nameCount.append(unreadCount);
           }

            li.append(itemHeader, itemBody);
            
            //클릭 이벤트 추가
            li.addEventListener("click", e => {
            	// 전역변수에 채팅방 번호, 상대 id, 상대 닉네임 저장
            	selectRoomId = li.getAttribute("room-id");
            	selectReceiverId = li.getAttribute("receiver-id");

            	selectReceiverName = li.children[1].children[0].children[0].innerText;

            	const unreadCountElem = li.querySelector(".unread-count");
            	if (unreadCountElem) {
                	unreadCountElem.remove();
            	}

           	 	// 모든 채팅방에서 select 클래스를 제거
            	const chattingItemList = chattingList.querySelectorAll(".chatting-item");
            	chattingItemList.forEach(item => item.classList.remove("select"));

            	// 현재 클릭한 채팅방에 select 클래스 추가
            	li.classList.add("select");

            	// 채팅 메시지가 있는 경우
            	// 비동기로 메시지 목록을 조회하는 함수 호출
            	selectChattingFn();

            	fetch("/heehee/chatting/read", {
                	method : "PUT",
                	headers : {"Content-Type": "application/json"},
                	body : JSON.stringify({"chatRoomId" : selectRoomId, "loginUserId" : loginMemberNo})
            	})
            	.then(resp => resp.text())
            	.then(result => console.log(result))
            	.catch(err => console.log(err));
        	});

            // 새로운 채팅방을 목록의 맨 위에 추가
            //chattingList.insertBefore(li, chattingList.children[1]);
        }
        if(existingChatRoom){
			chattingList.querySelector(`li[room-id="${room.id}"]`).remove();
			chattingList.appendChild(existingChatRoom);
		}
		else if(li){
			chattingList.appendChild(li);
		}
    }
}