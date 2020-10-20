import styled from 'styled-components'

export const HuffmanCodewordRow = styled.div`
    display: flex;
    flex-direction: column;
    max-width: 100%;
    padding:0 20px;
    align-items:center;

    .codeword{
        font-size: 0.9rem;
        background-color: rgb(93,103,134);
        padding: 6px;
        border-radius: 4px;
        overflow-x: auto;
        max-width: calc(100vw - 40vw);
        display: flex;
    }

    &>div{
        display: flex;
        margin: 10px;
        font-size: 0.9rem;

        .ascii{
            font-weight: bold;
            color: aliceblue;
            margin-right: 20px;
        }

        .code{
            font-weight: bold;
            color: rgb(73,210,128);
            margin-right:10px;
        }

        .codevalue{
            margin-left:10px;
            font-weight: bold;
            color: rgb(73,210,128);
        }
    }
` 