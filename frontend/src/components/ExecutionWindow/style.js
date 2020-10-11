import styled from "styled-components";
import { PRIMARY } from "../../constants/colors";

export const Container = styled.div`
  background-color: rgb(44, 49, 68);
  margin-left: 20px;
  border-radius: 10px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  flex: 1;
  justify-content: center;
  align-items: center;
`;

export const OnProcessing = styled.section`
  h2 {
    color: rgb(73, 210, 128);
    margin-bottom: 40px;
  }

  &>div{
    display: flex;
    justify-content: center;
  }
`;

export const Steps = styled.section`
  display:flex;
  flex-direction:column;
  width:100%;
  height:100%;
`
export const Buttons = styled.section`
  display:flex;
  align-self:center;
  justify-content:center;

  button{
    margin: 10px;
  }
`

export const StepsCanva = styled.div`
  width:100%;
  height:100%;

  header{
    display: flex;
    justify-content: space-between;
    margin: 15px 10px;

    .counter{
      color:${PRIMARY}
    }

    .codification-title{
      color:white;
    }
  }
`

export const ScroolingList = styled.div`
    overflow-y: auto;
    max-height: calc(100% - 100px)
`
