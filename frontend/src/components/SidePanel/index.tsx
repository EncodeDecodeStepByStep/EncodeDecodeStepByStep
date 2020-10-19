import React from "react";
import { useOnProcessing, useFinishedCodification } from '../../context'
import {Menu} from './Menu'
import {Text} from './Text'

export const SidePanel = () => {

  const [onProcessing, ] = useOnProcessing();
  const [onFinishedCodification, ] = useFinishedCodification();

  function renderText(){
    if(onFinishedCodification){
      return <Text/>
    }
  }

  function renderMenuOption() {
    if(!onProcessing && !onFinishedCodification){
      return <Menu/>
    }
  }

  return (
    <>
      {renderText()}
      {renderMenuOption()}
    </>

  );
};
