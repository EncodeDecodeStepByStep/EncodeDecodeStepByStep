import React from "react";
import {useIndex, useCodewords} from '../../../context'
import { Typografy } from "../../Typografy";
import { Container} from './style';

export const Text = () => {

  const [index,] = useIndex();
  const [codewords,] = useCodewords();


  function renderText(){
    if(index>0){
      let finalString = '';
      for(let i =0;i<index;i++){
        if(codewords[i]){
          finalString += codewords[i].value;
        }
      }
      return <p>{finalString}</p>
    }    
  }
  return (
    <Container>
      <Typografy.SUBTITLE className="text-title" text="Texto"/>
      {renderText()}
    </Container>
  )
};
