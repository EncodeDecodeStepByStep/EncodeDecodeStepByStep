import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import { HashRouter } from "react-router-dom";
import { Routes } from "./routes/routes";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "react-toggle/style.css";

import * as serviceWorker from "./serviceWorker";
import {
  ProcessingProvider,
  CodificationMethodProvider,
  FinishedCodificationProvider,
  CodewordsProvider,
  IndexProvider,
  GoulombDivisorProvider,
  ThemeProvider,
  CodingDecodingProvider,
} from "./context";

ReactDOM.render(
  <React.StrictMode>
    <ThemeProvider>
      <FinishedCodificationProvider>
        <CodificationMethodProvider>
          <ProcessingProvider>
            <CodewordsProvider>
              <IndexProvider>
                <GoulombDivisorProvider>
                  <CodingDecodingProvider>
                    <HashRouter>
                      <Routes />
                      <ToastContainer />
                    </HashRouter>
                  </CodingDecodingProvider>
                </GoulombDivisorProvider>
              </IndexProvider>
            </CodewordsProvider>
          </ProcessingProvider>
        </CodificationMethodProvider>
      </FinishedCodificationProvider>
    </ThemeProvider>
  </React.StrictMode>,
  document.getElementById("root")
);

serviceWorker.unregister();
