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
  max-height: 80px;

  img{
    height:65px;
  }

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
        background-color:#ddd;
        border-radius:5px;
        padding:10px;
        display:flex;
        flex-direction:column;

        .devname{
          color:#333;
        }

        .social-media{
          margin-top:10px;

          a{
            margin-right:10px;
          }
        }
      }
    }
  }
`;
