import React from 'react';
import { Switch, Route } from 'react-router-dom';
import { Main } from '../pages/main';

export const Routes = () => {

    return (
        <Switch>       
            <Route path="/" exact>
                <Main />
            </Route>

            <Route path="/main" exact>
                <Main />
            </Route>
        </Switch>
    )
}
