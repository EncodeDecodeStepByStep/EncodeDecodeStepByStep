import React from 'react';

import {Primary, Click} from './style';
import {Icon} from '../index'

type ButtonProps = { 
    children: string,
    onClick : Function,
    icon?: Icon,
    isSelected?:boolean;
    disabled?:boolean;
}

function handleClick(e:any, disabled:boolean, onClick:Function){
    if(!disabled){
        onClick(e);
    }
}

export class Button{

   

  static PRIMARY  = (props:ButtonProps) =>(
    
      <Primary onClick={(e)=>handleClick(e, props.disabled, props.onClick)} isSelected={props.isSelected} disabled={props.disabled}>
          {props.icon}
          {props.children}
      </Primary>
  );

  static CLICK  = (props:ButtonProps) =>(
    <Click onClick={(e)=>handleClick(e, props.disabled, props.onClick)} isSelected={props.isSelected} disabled={props.disabled}>
        {props.icon}
        {props.children}
    </Click>
);
}
