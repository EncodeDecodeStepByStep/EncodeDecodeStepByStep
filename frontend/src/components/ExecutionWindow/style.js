import styled, { css } from "styled-components";
import { BLUE_GRAY, PRIMARY, WHITE } from "../../constants/colors";

export const Container = styled.div`
  background-color: ${(props) => (props.isDark ? BLUE_GRAY : WHITE)};
  margin-left: 20px;
  border-radius: 10px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  flex: 1;
  justify-content: center;
  align-items: center;

  .waiting-message{
    font-size:12px;
    color: ${(props) => (props.isDark ? "white" : "black")};
  }
  
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
    color: ${(props) => (props.isDark ? "white" : "black")};
  }
`;

export const OnProcessing = styled.section`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  svg {
    width: fit-content;
  }

  h2 {
    color: ${PRIMARY};
    margin-bottom: 20px;
  }

  & > div {
    display: flex;
    justify-content: center;
  }

  button {
    margin-top: 15px;
  }

 

  .percentage-value {
    margin-top: 10px;
    display: flex;
    align-items: center;

    span {
      color: ${PRIMARY};
      font-weight: bold;
      margin-right: 10px;
    }
  }

  p {
    color: ${(props) => (props.isDark ? "white" : "black")};
    font-weight: bold;
    margin: 15px 0;
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
      color: ${(props) => (props.isDark ? "white" : "black")};
    }
  }
`;

export const ScroolingList = styled.div`
  ${(props) =>
    props.scrool &&
    css`
      overflow-y: auto;
      max-height: calc(100% - 100px);
    `}
`;
