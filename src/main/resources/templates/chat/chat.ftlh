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


    <script>
        const userList = ['John Doe', 'Jane Doe', 'Kaliev1910'];
        let activeUserIndex = 0;

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
            return [
                {sender: 'Me', message: 'Hello ' + user + ', how are you?'},
                {sender: user, message: 'Hi, I\'m good. How about you?'}
            ];
        }

        function displayChatMessages(messages) {
            const chatMessagesElement = document.getElementById('chat-messages');
            chatMessagesElement.innerHTML = '';
            messages.forEach(message => {
                const messageElement = document.createElement('div');
                messageElement.className = 'message ' + (message.sender === 'Me' ? 'sent' : 'received');
                messageElement.textContent = message.sender + ': ' + message.message;
                chatMessagesElement.appendChild(messageElement);
            });
        }

        document.getElementById('send-button').addEventListener('click', sendMessage);

        document.getElementById('message-input').addEventListener('keypress', function (event) {
            if (event.keyCode === 13) {
                sendMessage();
            }
        });

        function sendMessage() {
            const messageInput = document.getElementById('message-input');
            const message = messageInput.value.trim();
            if (message !== '') {
                const chatMessages = document.getElementById('chat-messages');
                const messageElement = document.createElement('div');
                messageElement.className = 'message sent';
                messageElement.textContent = '// ' + message;
                chatMessages.appendChild(messageElement);
                messageInput.value = '';
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            initUserList();
            switchChat(activeUserIndex);
        });
    </script>


</@main.layout>
