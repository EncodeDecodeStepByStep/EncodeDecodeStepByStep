import axios from 'axios';

const api = axios.create({
    baseURL:"http://localhost:8080",
    headers: { "Content-Type": "application/json; charset=UTF-8" }
})

export default api;