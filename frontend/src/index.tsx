import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import { HashRouter } from "react-router-dom";
import { Routes } from "./routes/routes";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import * as serviceWorker from "./serviceWorker";
import {
  ProcessingProvider,
  CodificationMethodProvider,
  FinishedCodificationProvider,
} from "./context";

ReactDOM.render(
  <React.StrictMode>
      <FinishedCodificationProvider>
        <CodificationMethodProvider>
          <ProcessingProvider>
            <HashRouter>
              <Routes />
              <ToastContainer />
            </HashRouter>
          </ProcessingProvider>
        </CodificationMethodProvider>
      </FinishedCodificationProvider>
  </React.StrictMode>,
  document.getElementById("root")
);

serviceWorker.unregister();
