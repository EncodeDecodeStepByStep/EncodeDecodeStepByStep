import styled from "styled-components";
import { DARKEST } from "../../constants/colors";

export const Container = styled.header`
  background-color: ${DARKEST};
  color: white;
  width: 100vw;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-height: 60px;

  .user-icon-container {
    cursor: pointer;
  }
`;

export const ModalContent = styled.div`
  display: flex;

  video {
    width: 250px;
  }

  nav {
    display: flex;

    ul {
      display: flex;
      flex-direction: column;
      margin-left: 20px;

      li {
        margin-bottom: 10px;
        font-size: 0.9rem;
        font-weight: bold;
      }
    }
  }
`;
