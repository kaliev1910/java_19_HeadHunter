<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout>
    <link type="text/css" rel="stylesheet" href="/static/css/chatStyle.css">

    <section class="msger">
        <header class="msger-header">
            <div class="msger-header-title">
                <i class="fas fa-comment-alt"></i> <@spring.message 'chat.title'></@spring.message>
            </div>
            <div class="msger-header-options">
                <span><i class="fas fa-cog"></i></span>
            </div>
        </header>

        <main class="msger-chat">
            <div class="msg left-msg">
                <div
                        class="msg-img"
                        style="background-image: url(https://image.flaticon.com/icons/svg/327/327779.svg)"
                ></div>

                <div class="msg-bubble">
                    <div class="msg-info">
                        <div class="msg-info-name"><@spring.message 'chat.botName'></@spring.message></div>
                        <div class="msg-info-time">12:45</div>
                    </div>

                    <div class="msg-text">
                        <@spring.message 'chat.botMessage'></@spring.message>
                    </div>
                </div>
            </div>

            <div class="msg right-msg">
                <div
                        class="msg-img"
                        style="background-image: url(https://image.flaticon.com/icons/svg/145/145867.svg)"
                ></div>

                <div class="msg-bubble">
                    <div class="msg-info">
                        <div class="msg-info-name">Sajad</div>
                        <div class="msg-info-time">12:46</div>
                    </div>

                    <div class="msg-text">
                        <@spring.message 'chat.yourMessage'></@spring.message>
                    </div>
                </div>
            </div>
        </main>

        <form class="msger-inputarea">
            <input type="text" class="msger-input" placeholder="<@spring.message 'chat.placeholder'></@spring.message>">
            <button type="submit" class="msger-send-btn"><@spring.message 'chat.send'></@spring.message></button>
        </form>
    </section>
    <script src="/static/js/chat.js"></script>
</@main.layout>
