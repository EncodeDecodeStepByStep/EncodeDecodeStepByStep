import createGlobalState from 'react-create-global-state';

const [useFinishedCodification, FinishedCodificationProvider] = createGlobalState(false)

export { useFinishedCodification, FinishedCodificationProvider }
