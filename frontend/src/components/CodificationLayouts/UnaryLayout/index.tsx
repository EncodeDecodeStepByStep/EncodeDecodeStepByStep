import React, { useEffect, useState } from "react";
import { PRIMARY } from "../../../constants/colors";
import { Icon } from "../../Icon";
import { UnaryCodewordRow } from "./styles";
import CodewordResponse from '../../../models/codewordEncodingResponse'

interface UnaryLayoutProps {
  code: CodewordResponse[];
  index: number;
}
export const UnaryLayout = (props: UnaryLayoutProps) => {
  const [showIndex, setShowIndex] = useState(0);

  useEffect(()=>{
    console.log("cu")
  },[props,props.code.length])

  useEffect(() => {
    setShowIndex(props.index);
  }, [ props.index]);

  const renderCodeword = (codeword:CodewordResponse) => {
      if(codeword){
        return (
            <UnaryCodewordRow>
              <span className="codeword">{codeword.value}</span>
              <div>
                <span className="utf8">UTF-8</span>
                <strong className="code">{codeword.value.length}</strong>
                <Icon.TransformTo size={15} color={PRIMARY} />
                <span className="codevalue">
                  {String.fromCharCode(codeword.value.length)}
                </span>
              </div>
            </UnaryCodewordRow>
          );
      }   
  };

  const renderCodewords = () => {
    const {code} = props
    console.log(code);
    const layoutArray = [];
    for (let i = 0; i < showIndex; i++) {
      let codeword = code[i];
      layoutArray.push(renderCodeword(codeword));
    }
    return layoutArray;
  };

  return <>{renderCodewords()}</>;
};
