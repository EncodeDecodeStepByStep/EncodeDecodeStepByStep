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
  FileToProcessProvider,
} from "./context";

ReactDOM.render(
  <React.StrictMode>
    <FileToProcessProvider>
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
    </FileToProcessProvider>
  </React.StrictMode>,
  document.getElementById("root")
);

serviceWorker.unregister();
