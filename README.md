# Bulb

#### A repository to mess around with Re-frame.

## Local development setup

- To kick off a repl from the command line: `clj --main figwheel.main -b dev -r`.
- To kick off a repl from within emacs: `C-c M-J`:
  - Then specify `figwheel-main`.
  - Then specify the `dev` build.
- To kick off an auto reloading Garden flow: `clj -Astyle` 

## Build a deployable artifact
- To build an optimised `index.js` file: `clj --main figwheel.main -bo prod`
- To build an optimised `index.css` file: `clj --main gardener.compiler --stylesheet bulb.styles.core/core --output-to resources/public/css/index.css --build-once true`
- To serve the app: `clj -m figwheel.main -s`
