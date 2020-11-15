import styled from 'styled-components';
import { BLUE_GRAY, DARKEST, LIGHTEST_GRAY, PRIMARY } from '../../constants/colors';

export const Container = styled.main`
    width:100%;
    height:100%;
    display:flex;
    align-items:center;
    justify-content:center;
    background-color:${props=>props.isDark? DARKEST: LIGHTEST_GRAY};
    

    .center-view{
        position: relative;
        top: -60px;
        padding:15px 30px;
        border-radius:12px;
        background-color:${props=>props.isDark? BLUE_GRAY: 'white'};
        display:flex;
        flex-direction:column;
        justify-content:center;

        .text{
            margin-bottom:20px;
            text-align:center;
            color:${props=>props.isDark? PRIMARY: 'black'};
        }

        p{
            color:${props=>props.isDark? 'white': 'black'};
            margin-bottom:10px;
            font-size:0.8rem;
        }

        .icon-container{
            display:flex;
            justify-content:center;
            animation:rotate360 infinite 2s forwards;
        }
    }
`
