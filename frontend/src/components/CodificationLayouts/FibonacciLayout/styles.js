import styled from "styled-components";
import { BLUEISH, DARKEST, LIGHTEST_GRAY, LIGHT_GRAY, PRIMARY } from "../../../constants/colors";

export const FibonacciCodewordRow = styled.div`
  display: flex;
  flex-direction: column;
  max-width: 100%;
  padding: 0 20px;
  align-items: center;

  .fibonacci-arrangment {
    display: flex;
    background-color:${props=> props.isDark?DARKEST:LIGHTEST_GRAY};
    padding: 20px 10px 10px 10px;
    border-radius: 10px;
    margin: 10px 0;
    align-items: center;
    flex-direction: column;    

    .fibonacci-column {
      display: flex;
      flex-direction: column;
      align-items: center;
      background-color:${props=> props.isDark?BLUEISH:LIGHT_GRAY};
      border-radius: 8px;
      padding: 5px 10px;
      margin: 0 5px;
    }

    .first-row {
      overflow-x: auto;
      overflow-y: hidden;
      max-width: 95%;
      padding-bottom: 10px;
      display: flex;

      .mark {
        background-color: ${PRIMARY};
        color: black;
      }
    }

    .second-row {
      display: flex;
      justify-content: center;
      margin: 10px;
      color:${props=> props.isDark?'white':'black'};

      .count strong {
        font-weight: bold;
        color:${props=> props.isDark?PRIMARY:'black'};
      }
    }
  }

  .first-row::-webkit-scrollbar {
    height: 3px;
  }

  .first-row::-webkit-scrollbar-track {
    background-color: #8e8e8e;
    border-radius: 1px;
    height: 3px;
  }

  .first-row::-webkit-scrollbar-thumb {
    background-color: #d6d6d6;
    border-radius: 1px;
    height: 3px;
  }

  .ascii {
    font-weight: bold;
    color:${props=> props.isDark?'white':'black'};
    margin-right: 5px;
  }

  .code {
    font-weight: bold;
    color:${props=> props.isDark?PRIMARY:'black'};
    margin-right: 10px;
  }

  .codevalue {
    margin-left: 10px;
    font-weight: bold;
    color:${props=> props.isDark?PRIMARY:'black'};
  }
`;
