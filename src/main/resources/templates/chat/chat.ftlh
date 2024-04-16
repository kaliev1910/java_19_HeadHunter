<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3 bg-light" style="max-width: 300px;">
                <h2>Employers</h2>
                <ul id="user-list" class="list-group">
                </ul>
            </div>
            <div class="col-md-9">
                <div class="chat-container">
                    <div id="chat-messages" class="messages">
                    </div>
                    <div class="input-group mb-3">
                        <label for="message-input"></label><input id="message-input" type="text" class="form-control"
                                                                  placeholder="Type your message here...">
                        <div class="input-group-append">
                            <button id="send-button" class="btn btn-primary">Send</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        let stompClient = null;
        let activeUserIndex = 0;
        let userList = ['John Doe', 'Jane Doe', 'Kaliev1910'];

        function connect() {
            let socket = new SockJS('/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/public', function(messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body));
                });
            });
        }

        function initUserList() {
            const userListElement = document.getElementById('user-list');
            userList.forEach((user, index) => {
                const listItem = document.createElement('li');
                listItem.className = 'list-group-item';
                listItem.textContent = user;

                listItem.addEventListener('click', () => switchChat(index));
                userListElement.appendChild(listItem);
            });
        }

        function switchChat(index) {
            activeUserIndex = index;
            const chatMessages = getChatMessagesForUser(userList[activeUserIndex]);
            displayChatMessages(chatMessages);
        }

        function getChatMessagesForUser(user) {
            // Здесь вы можете отправить запрос на сервер, чтобы получить сообщения чата для выбранного пользователя
            // Например, с помощью WebSocket или AJAX запроса к серверу
            // Вернуть полученные сообщения в виде массива объектов { sender: 'senderName', message: 'messageContent' }
            return [];
        }

        function displayChatMessages(messages) {
            const chatMessagesElement = document.getElementById('chat-messages');
            chatMessagesElement.innerHTML = '';
            messages.forEach(message => {
                const messageElement = document.createElement('div');
                messageElement.className = 'message ' + (message.sender === ${username} ? 'sent' : 'received');
                messageElement.textContent = message.sender + ': ' + message.message;
                chatMessagesElement.appendChild(messageElement);
            });
        }

        function sendMessage() {
            const messageInput = document.getElementById('message-input');
            const message = messageInput.value.trim();
            if (message !== '') {
                stompClient.send("/app/chat.sendMessage", {}, JSON.stringify({
                    sender: 'Me',
                    content: message,
                    type: 'CHAT'
                }));
                messageInput.value = '';
            }
        }

        document.getElementById('send-button').addEventListener('click', sendMessage);

        document.getElementById('message-input').addEventListener('keypress', function (event) {
            if (event.keyCode === 13) {
                sendMessage();
            }
        });

        document.addEventListener('DOMContentLoaded', function () {
            initUserList();
            switchChat(activeUserIndex);
            connect();
        });
    </script>
</@main.layout>
