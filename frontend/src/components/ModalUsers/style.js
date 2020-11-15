import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;

  p{
    font-size: 0.8rem;
    line-height: 27px;
    text-align: justify;
    text-indent: 10px;
    margin-bottom: 10px;
  }

  .teamwork-container{
      display:flex;
      flex-direction:row;
      justify-content: space-around;
      margin-top:15px;
    video {
      width: 250px;
    }

    ul {
      display: flex;
      flex-direction: column;
      margin-left: 0;
      margin-right:10px;

      li {
        margin-bottom: 10px;
        font-size: 0.9rem;
        font-weight: bold;
        background-color: #ddd;
        border-radius: 5px;
        padding: 10px;
        display: flex;
        flex-direction: column;

        .devname {
          color: #333;
        }

        .social-media {
          margin-top: 10px;

          a {
            margin-right: 10px;
          }
        }
      }
    }
  }
`;
