import createGlobalState from 'react-create-global-state';
const [useCodificationMethod, CodificationMethodProvider] = createGlobalState(-1)

export { useCodificationMethod, CodificationMethodProvider }
