import styled, {css} from "styled-components";
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

export const OnError = styled.div`
  img {
    margin-bottom: 16px;
  }

  h2 {
    margin-bottom: 20px;
    text-align: center;
    font-size: 22px;
    color: ${PRIMARY};
  }

  p {
    color: white;
  }
`;

export const OnProcessing = styled.section`

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  p{
    color:white;
    font-weight:bold;
    margin:15px 0;
  }

  svg{
    width:fit-content;
  }

  h2 {
    color: rgb(73, 210, 128);
    margin-bottom: 20px;
  }

  & > div {
    display: flex;
    justify-content: center;
  }

  .percentage-value {
    color: rgb(73, 210, 128);
    font-weight: bold;
    margin-top: 10px;
  }
`;

export const Steps = styled.section`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;
export const Buttons = styled.section`
  display: flex;
  align-self: center;
  justify-content: center;
  position: fixed;
  bottom: 30px;

  button {
    margin: 10px;
  }
`;

export const StepsCanva = styled.div`
  width: 100%;
  height: 100%;

  header {
    display: flex;
    justify-content: space-between;
    margin: 15px 10px;

    .counter {
      color: ${PRIMARY};
    }

    .codification-title {
      color: white;
    }
  }
`;

export const ScroolingList = styled.div`
  ${props => props.scrool && css`
    overflow-y: auto;
    max-height: calc(100% - 100px);
  `}
`;
