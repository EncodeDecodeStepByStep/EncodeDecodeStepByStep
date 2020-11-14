import styled from "styled-components";
import { BLUE_GRAY, PRIMARY, WHITE } from "../../../constants/colors";

export const Container = styled.div`
  background-color: ${(props) => (props.isDark ? BLUE_GRAY : WHITE)};
  border-radius: 10px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  width: 30%;
  overflow-y: auto;

  .text-title {
    color: ${PRIMARY};
    text-align: center;
  }

  p {
    margin-top: 10px;
    text-align: center;
    background-color: transparent;
    color: ${(props) => (props.isDark ? "white" : "black")};

    .actual-symbol {
      color: ${PRIMARY};
      font-weight: bold;
    }
  }
`;
