const fs = require('fs');
const path = require('path');

const filePath = path.join(
  __dirname,
  '../nitrogen/generated/ios/NitroRestart-Swift-Cxx-Bridge.hpp'
);

let content = fs.readFileSync(filePath, 'utf8');

// NON_NULL macro not found, add it
if (!content.includes('#ifndef NON_NULL')) {
  content = content.replace(
    /#include <memory>/,
    `#include <memory>

#ifndef NON_NULL
#define NON_NULL _Nonnull
#endif`
  );

  fs.writeFileSync(filePath, content, 'utf8');
  console.log('✅ NON_NULL macro added');
} else {
  console.log('ℹ️  NON_NULL macro already exists');
}
