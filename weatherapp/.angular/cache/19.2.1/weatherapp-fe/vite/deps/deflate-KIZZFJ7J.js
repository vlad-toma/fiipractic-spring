import {
  inflate_1
} from "./chunk-QM7TJVTA.js";
import {
  BaseDecoder
} from "./chunk-JSOACJM5.js";
import "./chunk-4MWRP73S.js";

// node_modules/geotiff/dist-module/compression/deflate.js
var DeflateDecoder = class extends BaseDecoder {
  decodeBlock(buffer) {
    return inflate_1(new Uint8Array(buffer)).buffer;
  }
};
export {
  DeflateDecoder as default
};
//# sourceMappingURL=deflate-KIZZFJ7J.js.map
