// Copyright 2018 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.skylarkbuildapi;

import com.google.devtools.build.lib.skylarkinterface.Param;
import com.google.devtools.build.lib.skylarkinterface.SkylarkCallable;
import com.google.devtools.build.lib.skylarkinterface.SkylarkModule;
import com.google.devtools.build.lib.skylarkinterface.SkylarkModuleCategory;
import com.google.devtools.build.lib.skylarkinterface.SkylarkValue;


/**
 *  The "config" module of the Build API.
 *
 *  This exposes methods to describe what kind of build setting (if any) a skylark rule is using
 *  the {@code build_setting} attr of the {@code rule(...)} function.
 */
@SkylarkModule(
    name = "config",
    namespace = true,
    category = SkylarkModuleCategory.BUILTIN,
    doc = "Note: This API is experimental and may change at any time."
        + ""
        + "<p>This is a top-level module for creating build setting descriptors which describe "
        + "what kind of build setting (if any) a skylark rule is. "
        + ""
        + "<p>ex: the following rule is marked as a build setting by setting the "
        + "<code>build_setting</code> parameter of the <code>rule()</code> function. Specifically "
        + "it is a build setting of type <code>int</code> and is a <code>flag</code> which means "
        + "this build setting is callable on the command line.<br><pre class=language-python>"
        + "  my_rule = rule(\n"
        + "    implementation = _impl,\n"
        + "    build_setting = config.int(flag = True),\n"
        + "    ...\n"
        + "  )</pre>")
//TODO(juliexxia): Create formal documentation for skylark build configuration efforts (b/112545834)
public interface SkylarkConfigApi extends SkylarkValue {

  static final String FLAG_ARG = "flag";
  static final String FLAG_ARG_DOC =
      "Whether or not this build setting is callable on the command line.";

  @SkylarkCallable(
      name = "int",
      doc = "An integer-typed build setting",
      parameters = {
        @Param(
            name = FLAG_ARG,
            type = Boolean.class,
            defaultValue = "False",
            doc = FLAG_ARG_DOC,
            named = true,
            positional = false)
      })
  BuildSettingApi intSetting(Boolean flag);

  @SkylarkCallable(
      name = "bool",
      doc = "A bool-typed build setting",
      parameters = {
        @Param(
            name = FLAG_ARG,
            type = Boolean.class,
            defaultValue = "False",
            doc = FLAG_ARG_DOC,
            named = true,
            positional = false)
      })
  BuildSettingApi boolSetting(Boolean flag);

  @SkylarkCallable(
      name = "string",
      doc = "A string-typed build setting",
      parameters = {
        @Param(
            name = FLAG_ARG,
            type = Boolean.class,
            defaultValue = "False",
            doc = FLAG_ARG_DOC,
            named = true,
            positional = false)
      })
  BuildSettingApi stringSetting(Boolean flag);

  @SkylarkCallable(
      name = "string_list",
      doc = "A string list-typed build setting",
      parameters = {
        @Param(
            name = FLAG_ARG,
            type = Boolean.class,
            defaultValue = "False",
            doc = FLAG_ARG_DOC,
            named = true,
            positional = false)
      })
  BuildSettingApi stringListSetting(Boolean flag);

  @SkylarkCallable(
      name = "label",
      doc = "A label typed build setting",
      parameters = {
        @Param(
            name = FLAG_ARG,
            type = Boolean.class,
            defaultValue = "False",
            doc = FLAG_ARG_DOC,
            named = true,
            positional = false)
      })
  BuildSettingApi labelSetting(Boolean flag);

  @SkylarkCallable(
      name = "label_list",
      doc = "A label list-typed build setting",
      parameters = {
        @Param(
            name = FLAG_ARG,
            type = Boolean.class,
            defaultValue = "False",
            doc = FLAG_ARG_DOC,
            named = true,
            positional = false)
      })
  BuildSettingApi labelListSetting(Boolean flag);

  /** The API for build setting descriptors. */
  @SkylarkModule(
      name = "BuildSetting",
      category = SkylarkModuleCategory.NONE,
      doc =
          "The descriptor for a single piece of configuration information. If configuration is a "
              + "key-value map of settings like {'cpu': 'ppc', 'copt': '-DFoo'}, this describes a "
              + "single entry in that map.")
  interface BuildSettingApi extends SkylarkValue {}
}
