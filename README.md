# Bulb

#### A repository to mess around with Re-frame.

## Local development setup
- To kick off a repl from the command line: `clj -Afigwheel-dev`.
- Alternatively, to kick off a repl from within emacs: `C-c M-J`:
  - Then specify `figwheel-main`.
  - Then specify the `dev` build.
- To kick off an auto reloading Garden flow: `clj -Agardener-dev` 

## Build and deploy an artifact
- Ensure the `resources/css/` and `resources/js/` directories are empty.
- To build an optimised `index.js` file: `clj -Afigwheel-prod`
- To build an optimised `index.css` file: `clj -Agardener-prod`
- To serve the app locally: `clj -Afigwheel-serve`
- To deploy the artifact: `firebase deploy`
