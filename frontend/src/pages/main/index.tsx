import React from 'react'
import { ExecutionWindow, SidePanel, Header } from '../../components'
import {Container, MainProgram} from './styles'

export const Main = () => {
 return (
    <Container>
        <Header/>
        <MainProgram>
           <SidePanel/>
           <ExecutionWindow/>
        </MainProgram>
    </Container>
 )
};
