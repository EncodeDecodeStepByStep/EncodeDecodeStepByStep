import styled from "styled-components";
import { BLUE_GRAY, WHITE } from "../../constants/colors";

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

