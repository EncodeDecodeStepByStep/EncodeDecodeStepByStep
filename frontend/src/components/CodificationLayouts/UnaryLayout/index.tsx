import React, { useEffect, useState } from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { UnaryCodewordRow } from "./styles";

interface UnaryLayoutProps {
  text: string;
  index: number;
  setLength:Function
}
export const UnaryLayout = (props: UnaryLayoutProps) => {
  const [splittedText, setSplittedText] = useState([]);
  const [showIndex, setShowIndex] = useState(0);

  useEffect(() => {
    const { text, setLength } = props;
    if (text) {
        const textSplitted = text.split("-");
        if(textSplitted.length){
            setLength(textSplitted.length)
            setSplittedText(textSplitted);
        }
      
    }
  }, [props, props.text]);

  useEffect(() => {
    setShowIndex(props.index);
  }, [ props.index]);

  const renderCodeword = (codeword: string) => {
      if(codeword){
        return (
            <UnaryCodewordRow>
              <span className="codeword">{codeword}</span>
              <div>
                <span className="utf8">UTF-8</span>
                <strong className="code">{codeword.length}</strong>
                <Icon.TransformTo size={15} color={PRIMARY} />
                <span className="codevalue">
                  {String.fromCharCode(codeword.length)}
                </span>
              </div>
            </UnaryCodewordRow>
          );
      }   
  };

  const renderCodewords = () => {
      const layoutArray = [];
    for (let i = 0; i < showIndex; i++) {
      let codeword = splittedText[i];
      layoutArray.push(renderCodeword(codeword));
    }
    return layoutArray;
  };

  return <>{renderCodewords()}</>;
};
