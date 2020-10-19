import api from './baseApi'

export async function encode(method:string, path:string, divisor:number){
    const data = (method==='goulomb') ? {path:path, divisor:divisor} : path;
    
    const response = await api.post(`/${method}/normal/encode`, data);
    
    
    return response;
}

export async function decode(method:string, path:string){
    const response = await api.post(`/${method}/normal/decode`, path);
    return response;
}

export async function nextStep(method:string){
    const response = await api.get(`http://localhost:8080/${method}/nextStep`);
    return response.data;
}

export async function progress(method:string){
    const response = await api.get(`http://localhost:8080/${method}/progressPercentage`);
    return response.data;
}


