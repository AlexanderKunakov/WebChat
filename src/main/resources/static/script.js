let stompClient;


function connect() {
    let socket = new SockJS("/messages/endpoint");
    stompClient = Stomp.over(socket)
    stompClient.connect({}, function (frame) {
        console.log("connected: " + frame)
        stompClient.subscribe('/chat/messages', function (response) {
            let data = JSON.parse(response.body);
            draw(data.from, data.message)
        })
    })
}

function draw(from, text) {
    console.log("drawing...")

    let $message = $($("#message_template").clone().html())
    $message.find('.from').html(from);
    $message.find('.text').html(text);
    $(".messages").append($message)
    return setTimeout(function () {
        return $message.addClass('appeared')
    }, 0)

}

function disconnect() {
    stompClient.disconnect()
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify(
        {
            'message': $("#type-field").val(),
            'from': document.getElementById("from-field").innerText
        }
        )
    )
}