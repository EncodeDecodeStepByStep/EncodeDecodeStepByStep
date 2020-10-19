import styled from 'styled-components'
import { DARKEST, PRIMARY } from '../../../constants/colors'

export const Header = styled.header`
    display:flex;
    justify-content:center !important;

    span{
        margin:0 8px;        
        font-weight:bold;
    }

    .stopbit{
        color:${PRIMARY};
    }   

    .rest, .unaryPart{
        color:white;
    }
`

export const Explanation = styled.div`
    display:flex;
    flex-direction: column;

    .first-line, .second-line{
        display:flex;
    }

    .first-line{
        margin: 10px;
        color: #666;
        align-items: center;

        strong {
            color: ${PRIMARY};
            font-size: 0.9rem;
        }

        span {
            color:white;
            font-size: 0.8rem;
            font-style: italic;
        }

        .prefix{
            margin-right:8px;
        }

        .sufix{
            margin:8px;
        }

        .result{
            margin-left:8px;
        }
    }

    .second-line{
        justify-content: center;
        .ascii{
            font-weight: bold;
            color: aliceblue;
            margin-right: 20px;
        }

        .codevalue{
            margin-left:10px;
            font-weight: bold;
            color: ${PRIMARY};
        }
    }        
`

export const GoulombCodewordRow = styled.div`
    display: flex;
    flex-direction: column;
    max-width: 100%;
    align-items:center;
    padding:10px;
    background-color: ${DARKEST};
    border-radius: 10px;
    margin: 10px;

    .codeword{
        font-size: 0.9rem;        
        display: flex;
        align-items: center;

        span{
            margin: 0 4px;
            border-radius: 4px;
            padding: 6px;
            background-color: rgb(93,103,134);
        }

        .stopbit{
            font-size:1.2rem;
            color:${PRIMARY};
            font-weight:bold;
        }     
    }
` 