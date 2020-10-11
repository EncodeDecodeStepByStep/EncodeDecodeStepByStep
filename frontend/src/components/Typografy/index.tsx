import React from 'react';
import {Emphasys, Subtitle} from './styles'

type TypografyProps = { 
   className?:string,
   text:string
}

export class Typografy{
  static EMPHASYS  = (props:TypografyProps) =>(
      <Emphasys className={props.className}>{props.text}</Emphasys>
  );

  static SUBTITLE  = (props:TypografyProps) =>(
    <Subtitle className={props.className}>{props.text}</Subtitle>
);


}
