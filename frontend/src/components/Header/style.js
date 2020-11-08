import styled from "styled-components";
import { DARKEST, GRAY, LIGHT_GRAY, PRIMARY } from "../../constants/colors";

export const Container = styled.header`

  background-color: ${props => props.isDark ? DARKEST: LIGHT_GRAY};
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

  .right-content{
    display:flex;   
    align-items:center;

    .toggle-container{
      display:flex;
      align-items:center;
      margin-right:20px;

      .react-toggle{
        margin-right:10px;
        transform:scale(0.8);
      }

      .toggle-name{
        font-weight:bold;
        color:${props=>props.isDark? 'white': 'black'}
      }
    }
  }

  .react-toggle--checked .react-toggle-track {
    background-color: ${PRIMARY};
  }

  .react-toggle--checked:hover:not(.react-toggle--disabled) .react-toggle-track{
    background-color: ${PRIMARY} !important;
  }

  .react-toggle-track{
    background-color:${GRAY}
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
