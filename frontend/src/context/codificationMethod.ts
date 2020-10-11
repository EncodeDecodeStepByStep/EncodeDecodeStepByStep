import createGlobalState from 'react-create-global-state';
import { CodificationMethod } from "../enums/CodificationMethod";
const [useCodificationMethod, CodificationMethodProvider] = createGlobalState(CodificationMethod.NO_ONE)

export { useCodificationMethod, CodificationMethodProvider }
