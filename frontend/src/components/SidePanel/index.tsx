import React from "react";
import { useOnProcessing, useFinishedCodification } from '../../context'
import {Menu} from './Menu'
import {Text} from './Text'

interface SidePanelProps{
  onError:boolean;
}

export const SidePanel = (props:SidePanelProps) => {

  const [onProcessing, ] = useOnProcessing();
  const [onFinishedCodification, ] = useFinishedCodification();

  function renderText(){
    if(!props.onError && onFinishedCodification){
      return <Text/>
    }
  }

  function renderMenuOption() {
    if(!props.onError && !onProcessing && !onFinishedCodification){
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
