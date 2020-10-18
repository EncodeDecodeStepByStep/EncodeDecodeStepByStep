import React from 'react'
import { ExecutionWindow, SideMenu, Header } from '../../components'
import {Container, MainProgram} from './styles'

export const Main = () => {
 return (
    <Container>
        <Header/>
        <MainProgram>
           <SideMenu/>
           <ExecutionWindow/>
        </MainProgram>
    </Container>
 )
};
