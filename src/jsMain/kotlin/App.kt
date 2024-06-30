import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.MessageEvent
import org.w3c.dom.WebSocket

fun main() {
    val chatArea = document.getElementById("chat") as HTMLTextAreaElement
    val inputField = document.getElementById("message-field") as HTMLInputElement
    val sendButton = document.getElementById("button-send")!!

    val webSocket = WebSocket("ws://localhost:8080/chat")

    webSocket.onmessage = { event: MessageEvent ->
        chatArea.value += event.data.toString() + "\n"
        Unit
    }

    sendButton.addEventListener("click", {
        val message = inputField.value
        if (message.isNotEmpty()) {
            webSocket.send(message)
            inputField.value = ""
        }
    })
}