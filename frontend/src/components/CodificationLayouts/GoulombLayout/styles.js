import styled from "styled-components";
import {
  BLUEISH,
  DARKEST,
  LIGHTEST_GRAY,
  LIGHT_GRAY,
  PRIMARY,
} from "../../../constants/colors";

export const Explanation = styled.div`
  .icon-container {
    margin: 0 10px;
  }

  display: flex;
  flex-direction: column;

  .first-line,
  .second-line {
    display: flex;
  }

  .first-line {
    margin: 10px;
    color: #666;
    align-items: center;

    strong {
      color: ${(props) => (props.isDark ? PRIMARY : "black")};
      font-size: 0.9rem;
    }

    span {
      color: ${(props) => (props.isDark ? "white" : "black")};
      font-size: 0.8rem;
      font-style: italic;
    }

    .prefix {
      margin-right: 8px;
    }

    .sufix {
      margin: 8px;
    }

    .result {
      margin-left: 8px;
    }
  }

  .second-line {
    justify-content: center;
    .ascii {
      font-weight: bold;
      color: ${(props) => (props.isDark ? "aliceblue" : "black")};
      margin-right: 20px;
    }

    .codevalue {
      margin-left: 10px;
      font-weight: bold;
      color: ${(props) => (props.isDark ? PRIMARY : "black")};
    }
  }
`;

export const GoulombCodewordRow = styled.div`
  display: flex;
  flex-direction: column;
  max-width: 100%;
  align-items: center;
  padding: 10px;
  background-color: ${(props) => (props.isDark ? DARKEST : LIGHTEST_GRAY)};
  border-radius: 10px;
  margin: 10px;

  .codeword {
    font-size: 0.9rem;
    display: flex;
    align-items: center;

    span {
      margin: 0 4px;
      border-radius: 4px;
      padding: 6px;
      background-color: ${(props) => (props.isDark ? BLUEISH : LIGHT_GRAY)};
    }

    .stopbit {
      font-size: 1.2rem;
      color: ${(props) => (props.isDark ? PRIMARY : "black")};
      font-weight: bold;
    }
  }
`;
