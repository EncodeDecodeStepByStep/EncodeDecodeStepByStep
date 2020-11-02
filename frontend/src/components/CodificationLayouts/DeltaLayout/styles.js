import styled from 'styled-components'
import { GRAY, DARKEST, PRIMARY, BLUE_GRAY } from '../../../constants/colors'


export const CodewordCount = styled.div`
    display:flex;
    justify-content:center;
    align-items:center;

    span{
        margin: 0 10px;
    }
`

export const SameCodeword = styled.div`
    display:flex;
    justify-content:center;
    align-items:center;
`


export const CodewordLayout = styled.div`
    
    span{
        background-color: ${BLUE_GRAY};
        padding: 6px 15px 6px 11px;
        border-radius:3px;
        padding:4px;
    }

    svg{
        position: relative;
        transform: translate(1px, -6px);
    }
`

export const DeltaCodewordRow = styled.div`
    display: flex;
    flex-direction: column;
    max-width: 100%;
    padding: 8px;
    margin:10px 0;
    align-items:center;
    background-color: ${GRAY};
    border-radius:10px;
    color:white;

    .delta-codeword-title{
        color:${PRIMARY};
    }

    .codeword{
        font-size: 0.9rem;        
        padding: 6px;
        border-radius: 4px;
        background-color:${BLUE_GRAY};
    }

    .first-codeword{
        display:flex;
        align-items:center;
        background-color:${GRAY};

        svg{
            margin:0 10px;
        }
    }

    &>div{
        display: flex;
        margin: 10px;
        font-size: 0.9rem;
        .codevalue{
            margin-left:10px;
            font-weight: bold;
            color: ${PRIMARY};
        }
    }
` 