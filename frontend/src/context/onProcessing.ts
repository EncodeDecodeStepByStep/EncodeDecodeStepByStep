import createGlobalState from 'react-create-global-state';

const [useOnProcessing, ProcessingProvider] = createGlobalState(false)

export { useOnProcessing, ProcessingProvider }
