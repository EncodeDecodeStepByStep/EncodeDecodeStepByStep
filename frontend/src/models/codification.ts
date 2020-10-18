import { ReactElement } from "react"

export class Codification{
    name:string;
    urlName:string;
    codificationType:  number;
    icon: ReactElement;

    constructor(name:string, urlName:string, codificationType:number, icon:ReactElement){
        this.name=name;
        this.urlName=urlName;
        this.codificationType = codificationType;
        this.icon = icon;
    }

}