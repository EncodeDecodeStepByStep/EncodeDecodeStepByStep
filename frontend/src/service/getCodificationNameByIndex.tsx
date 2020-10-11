import codifications from '../constants/codifications'
export const getCodificationNameByIndex = (index:number) =>{
    return codifications.find((codification)=>{
        return codification.index ==index;
    })
}