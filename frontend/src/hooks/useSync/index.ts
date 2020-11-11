import {api} from '../index'

export async function sync(){
    return await api.get(`/sync`);
}



