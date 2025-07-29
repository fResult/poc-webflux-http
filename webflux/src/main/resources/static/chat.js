addEventListener('load', onLoad)

/**
 * @param ev { Event }
 */
function onLoad(ev) {
  const messages = document.querySelector('#messages')
  const sendButton = document.querySelector('#send')
  const messageText = document.querySelector('#message')
  const webSocket = new WebSocket('ws://localhost:8080/ws/chat')

  webSocket.addEventListener('message', onMessage(messages))
  messageText.addEventListener('keydown', onKeyEnterDown(webSocket, messageText))
  sendButton.addEventListener('click', onClick(webSocket, messageText))
}

/**
 * @param messages { HTMLElement }
 * @returns { (ev: MessageEvent<string>) => void}
 */
function onMessage(messages) {
  return ev => {
    const element = document.createElement('div')
    element.innerText = ev.data
    messages.appendChild(element)
  }
}

/**
 * @param webSocket { WebSocket }
 * @param messageInput { HTMLInputElement }
 * @returns { void }
 */
function send(webSocket, messageInput) {
  const msgValue = messageInput.value
  messageInput.value = ''
  webSocket.send(JSON.stringify({text: msgValue.trim()}))
}

/**
 * @param webSocket { WebSocket }
 * @param messageInput { HTMLInputElement }
 * @returns { (ev: KeyboardEvent) => void }
 */
function onKeyEnterDown(webSocket, messageInput) {
  return (ev) => {
    if (ev.key === 'Enter') {
      send(webSocket, messageInput)
    }
  }
}

/**
 * @param webSocket { WebSocket }
 * @param messageInput { HTMLInputElement }
 * @returns { (ev: MouseEvent) => void }
 */
function onClick(webSocket, messageInput) {
  return (ev) => {
    send(webSocket, messageInput)
    ev.preventDefault()
  }
}
