import React from 'react';
import {Container} from './style'
import {useTheme} from '../../context'
import { Typografy } from '../Typografy';
import { Icon } from '../Icon';
import { SyncState } from '../../enums/SyncState';
import { Button } from '../Button';

export const SyncLoader = ({isSync, setIsSync}) =>{

    const [theme] = useTheme();

    return (
        <Container isDark={theme}>
            <div className="center-view">
                {isSync == SyncState.UNSYNC ?
                    <>
                        <Typografy.SUBTITLE className="text" text="Initializing the services"/>
                        <p>Wait while we initialize the application services</p>
                        <span className="icon-container">
                            <Icon.Loader size={40} color={theme?'white':'black'}/>
                        </span>                        
                    </>
               :
                    <>
                        <Typografy.SUBTITLE className="text" text="Failed to connect to the services"/>
                        <Button.PRIMARY isDark={theme} onClick={()=>{
                            setIsSync(SyncState.UNSYNC)
                        }}>Try again</Button.PRIMARY>
                    </>
                }
            </div>
        </Container>
    )
}