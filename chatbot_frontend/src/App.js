// src/App.js
import React, { useState, useEffect } from 'react';
import './App.css';


const App = () => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');

  const sendMessage = async () => {
    if (input.trim()) {
      const newMessage = { text: input, sender: 'user' };
      setMessages([...messages, newMessage]);

      // Send message to backend and get response
      const response = await fetch('http://localhost:8080/messages', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text: input })
      });
      const data = await response.json();
      setMessages([...messages, newMessage, { text: data.reply, sender: 'bot' }]);

      setInput('');
    }
  };

  useEffect(() => {
    const chatContainer = document.querySelector('.messages');
    chatContainer.scrollTop = chatContainer.scrollHeight;
  }, [messages]);

  return (
    <div className="App">
      
      <div className="chat-container">
        <h3 className="ChatPoint">How can I help you ?</h3>
        <div className="messages">
          {messages.map((message, index) => (
            <div key={index} className={`message ${message.sender}`}>
              {message.text}
            </div>
          ))}
        </div>
        <div className="input-container">
          <input
            type="text"
            value={input}
            onChange={e => setInput(e.target.value)}
            onKeyDown={e => e.key === 'Enter' && sendMessage()}
          />
          <button onClick={sendMessage}>Send</button>
        </div>
      </div>
    </div>
  );
};

export default App;