import styled from "styled-components";
import { DARKEST, GRAY, LIGHT_GRAY, PRIMARY } from "../../constants/colors";

export const Container = styled.header`
  background-color: ${(props) => (props.isDark ? DARKEST : LIGHT_GRAY)};
  color: white;
  width: 100vw;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-height: 80px;

  img {
    cursor: pointer;
    height: 65px;
  }

  .right-content {
    display: flex;
    align-items: center;

    .toggle-container {
      display: flex;
      align-items: center;
      margin-right: 20px;

      .react-toggle {
        margin-right: 10px;
        transform: scale(0.8);
      }

      .toggle-name {
        font-weight: bold;
        color: ${(props) => (props.isDark ? "white" : "black")};
      }
    }
  }

  .react-toggle--checked .react-toggle-track {
    background-color: ${PRIMARY};
  }

  .react-toggle--checked:hover:not(.react-toggle--disabled)
    .react-toggle-track {
    background-color: ${PRIMARY} !important;
  }

  .react-toggle-track {
    background-color: ${GRAY};
  }

  .user-icon-container {
    cursor: pointer;
  }
`;