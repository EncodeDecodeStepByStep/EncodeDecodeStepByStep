import styled from 'styled-components'
import { DARKEST, PRIMARY } from '../../../constants/colors'

export const Count = styled.div`
    color:white;
    background-color:${DARKEST};
    padding:10px;
    border-radius:10px;

    .line{
        margin:8px auto;
        justify-content: center;
        align-items: center;
        display: flex;

        &>span{
            margin:0 10px;
        }
    }
`

export const TreeContainer = styled.div`
    background-color:${DARKEST};
    padding:10px;
    border-radius:10px;
    margin:8px;

    .normal-path{
        fill: transparent;
        stroke:black;
	    stroke-width: 1px;
    }


    .red-path{
        fill: transparent;
        stroke:red;
	    stroke-width: 3px;
    }

    .write-path{
        fill: transparent;
        stroke:${PRIMARY};
	    stroke-width: 3px;
    }

    .node circle {
        fill: ${PRIMARY};
        stroke-width: 0px;
    }

    .node text {
        font-size: 11px;
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        background-color: black;
        fill: white;
    }

`

export const Container = styled.div`
    display:flex;

    h3{
        color:white;
        text-align:center;
        margin-bottom:10px;
    }

    .first-column{
        display:flex;
        flex-direction: column;
        overflow-y: auto;
        max-height: calc(100vh - 250px);

        .counters{
            display:flex;
            flex-direction:row;

            &> div {
                margin: 0 8px 8px;
                flex: 1;
            }
        }       
    }

    .second-column{
        padding: 6px;
        overflow-y: auto;
        max-height: calc(100vh - 250px);
        margin-left: 8px;
        border-radius: 10px;
        background-color: #1b202c;
        flex: 1;
    }
`

export const HuffmanCodewordRow = styled.div`

    align-items:center;
    font-size: 0.9rem;
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 8px 5px;

    .codeword{
        font-size: 0.9rem;
        background-color: rgb(93,103,134);
        padding: 6px;
        border-radius: 4px;
        margin-right: 10px;
    }

    .codevalue{
        margin-left:10px;
        font-weight: bold;
        color: rgb(73,210,128);
    }
` 